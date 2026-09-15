package org.example.designpatterns.behavioural.state.goodcode;

/*
    use cases:
    1. A mobile app where the navigation behaviour changes based on whether the user is
       logged in or logged out.
    2. Buttons that change behaviour based on state(enabled, disabled, pressed).
    3. TCP Connections - behaviour can change based on whether the connection is listening, connecting or closed.
 */
public class DirectionService {

    // holds the state
    private TransportationMode mode;

    public DirectionService(TransportationMode mode){
        this.mode = mode;
    }

    public void setMode(TransportationMode mode) {
        this.mode = mode;
    }

    /*
        Delegating the work to the current state concrete class
     */
    public int getETA(){
        return mode.getETA();
    }

    /*
        Delegating the work to the current state concrete class
     */
    public void getDirection(){
        mode.getDirection();
    }
}
