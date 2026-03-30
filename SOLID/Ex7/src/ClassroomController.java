public class ClassroomController {

    private DeviceRegistry registry;

    public ClassroomController(DeviceRegistry registry) {
        this.registry = registry;
    }

    public void startClass() {

        for (PowerControl p : registry.getByCapability(PowerControl.class)) {
            p.powerOn();
        }

        for (InputConnectable p : registry.getByCapability(InputConnectable.class)) {
            p.connectInput("HDMI-1");
        }

        for (BrightnessControl b : registry.getByCapability(BrightnessControl.class)) {
            b.setBrightness(60);
        }

        for (TemperatureControl t : registry.getByCapability(TemperatureControl.class)) {
            t.setTemperatureC(24);
        }

        for (AttendanceScanning s : registry.getByCapability(AttendanceScanning.class)) {
            int present = s.scanAttendance();
            System.out.println("Attendance scanned: present=" + present);
        }
    }

    public void endClass() {

        System.out.println("Shutdown sequence:");

        for (PowerControl p : registry.getByCapability(PowerControl.class)) {
            p.powerOff();
        }
    }
}