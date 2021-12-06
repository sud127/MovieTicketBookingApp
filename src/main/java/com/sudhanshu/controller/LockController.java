package com.sudhanshu.controller;

import com.sudhanshu.model.Lock;
import com.sudhanshu.model.Seat;
import com.sudhanshu.model.Show;

import java.time.Instant;
import java.util.*;

public class LockController {

    private int lockTimeout;

    private Map<Show, Map<Seat,Lock>> lockMap = new HashMap<>();

    public LockController(int lockTimeout) {
        this.lockMap = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }
    
    synchronized public void lockSeats(Show show,List<Seat> seats,
                                         String user) {
        for (Seat seat : seats) {
            if (seatLocked(show, seat)) {
                throw new RuntimeException("Seat is already blocked!");
            }
        }

        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    public void unlockSeats(Show show,List<Seat> seats,String user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    public boolean validateLock(Show show, Seat seat,String user) {
        if(seatLocked(show, seat)
                && lockMap.get(show).get(seat).getLockedByUser().equals(user))
            return true;
        return false;
    }

    public List<Seat> getLockedSeats(Show show) {
         List<Seat> lockedSeats = new ArrayList<>();
        if (!lockMap.containsKey(show)) {
            return lockedSeats;
        }
        for (Seat seat : lockMap.get(show).keySet()) {
            if (seatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private void unlockSeat( Show show,  Seat seat) {
        if (!lockMap.containsKey(show)) {
            return;
        }
        lockMap.get(show).remove(seat);
    }

    private void lockSeat( Show show,  Seat seat,  String user,  int timeout) {
        if (!lockMap.containsKey(show)) {
            lockMap.put(show, new HashMap<>());
        }

         Lock lock = new Lock(seat, show, timeout, new Date(), user);
         lockMap.get(show).put(seat, lock);
    }

    private boolean seatLocked( Show show,  Seat seat) {
        if(lockMap.containsKey(show)
              && lockMap.get(show).containsKey(seat)
                && !lockExpired(show, seat ))
            return true;

        return false;
    }

    public boolean lockExpired(Show show, Seat seat) {

        Instant lockDuration = lockMap.get(show).get(seat).getLockTime().toInstant().plusSeconds(lockTimeout);
        Instant currentTime = new Date().toInstant();

        return lockDuration.isBefore(currentTime);
    }
}
