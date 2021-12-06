import com.sudhanshu.controller.*;
import com.sudhanshu.model.*;
import com.sudhanshu.util.ScreenUtil;
import com.sudhanshu.util.ShowUtil;
import com.sudhanshu.util.TheatreUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class MainTest {


    private TicketBookingController bookingController;
    private LockController lockController;
    private PaymentController paymentsController;
    private MovieController movieController;
    private SeatController seatController;
    
    private TheatreUtil theatreUtil;
    private ScreenUtil screenUtil;
    private ShowUtil showUtil;

    @Before
    public void setUp() throws Exception {

        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("src/main/java/com/sudhanshu/config/Config.properties");
        prop.load(input);

        showUtil = new ShowUtil();
        bookingController = new TicketBookingController(showUtil);
        lockController = new LockController(Integer.valueOf(prop.getProperty("TimeOut")));
        paymentsController = new PaymentController(bookingController,lockController,Integer.valueOf(prop.getProperty("Retries")));
        movieController = new MovieController();
        seatController = new SeatController(bookingController,lockController);

        screenUtil = new ScreenUtil();
        theatreUtil = new TheatreUtil(screenUtil);


    }

    @Test
    public void testCase1() {

         String user1 = "u1";
         Movie movie = movieController.createMovie("TestMovie", "Thriller");
         Theatre theatre = theatreUtil.createTheatre("Theatre 1");
         Screen screen = theatreUtil.addScreenInTheatre("Screen1", theatre);

         List<Seat> screen1Seats = addSeats(screenUtil, screen, 10, 10, "Premium");

//         for(Seat s : screen1Seats) {
//             System.out.println(s.getSeatId());
//         }

        Show show = showUtil.createShow(movie, screen, new Date(),new Date(),theatre);

        List<Seat> availableSeatsForUser1 = seatController.getAvailableSeats(show);

        validateSeatList(availableSeatsForUser1, screen1Seats, new ArrayList<>());

        List<Seat> selectedSeatsUser1 = new ArrayList<>();

        selectedSeatsUser1.add(screen1Seats.get(0));
        selectedSeatsUser1.add(screen1Seats.get(1));
        selectedSeatsUser1.add(screen1Seats.get(3));

        String bookingId = bookingController.bookTicket(user1, movie.getId() ,show.getId(), selectedSeatsUser1,user1);
        paymentsController.paymentSuccess(bookingId, user1);

        List<Seat> availableSeatsForUser2 = seatController.getAvailableSeats(show);
        validateSeatList(availableSeatsForUser2, screen1Seats, selectedSeatsUser1);
    }

    @Test
    public void testCase2() {

        String user1 = "u1";
        Movie movie = movieController.createMovie("TestMovie", "Thriller");
        Theatre theatre = theatreUtil.createTheatre("Theatre 1");
        Screen screen = theatreUtil.addScreenInTheatre("Screen1", theatre);

        List<Seat> screen1Seats = addSeats(screenUtil, screen, 10, 10, "Premium");

        Show show = showUtil.createShow(movie, screen, new Date(),new Date(),theatre);

        List<Seat> availableSeatsForUser1 = seatController.getAvailableSeats(show);

        validateSeatList(availableSeatsForUser1, screen1Seats, new ArrayList<>());

        List<Seat> selectedSeatsUser1 = new ArrayList<>();

        selectedSeatsUser1.add(screen1Seats.get(0));
        selectedSeatsUser1.add(screen1Seats.get(1));
        selectedSeatsUser1.add(screen1Seats.get(3));

        String bookingId = bookingController.bookTicket(user1, movie.getId() ,show.getId(), selectedSeatsUser1,user1);
        paymentsController.paymentFailed(bookingId, user1);

        List<Seat> availableSeatsForUser2 = seatController.getAvailableSeats(show);
        validateSeatList(availableSeatsForUser2, screen1Seats, selectedSeatsUser1);
    }


    private List<Seat> addSeats(ScreenUtil screenUtil, Screen screen, int numRows, int totalSeatsInRow, String seatType) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < totalSeatsInRow; j++) {
                Seat seat = screenUtil.addSeatInScreen(String.valueOf(i), String.valueOf(j), seatType, screen);
                seats.add(seat);
            }
        }
        return seats;
    }

    private void validateSeatList(List<Seat> seatList, List<Seat> allSeatsInScreen, List<Seat> blockedSeats) {
        for (Seat requestedSeat: allSeatsInScreen) {
            if (!blockedSeats.contains(requestedSeat)) {
                Assert.assertTrue(seatList.contains(requestedSeat));
            }
        }
    }

}
