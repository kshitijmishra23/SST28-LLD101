public class Projector implements PowerControl, InputConnectable {

    private boolean on;

    @Override
    public void powerOn() {
        on = true;
    }

    @Override
    public void powerOff() {
        on = false;
        System.out.println("Projector OFF");
    }

    @Override
    public void connectInput(String port) {
        if (on) {
            System.out.println("Projector ON (" + port + ")");
        }
    }
}