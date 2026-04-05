public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Projector pj = reg.getFirst(Projector.class);
        pj.powerOn();
        pj.connectInput("HDMI-1");

        reg.getFirst(BrightnessControllable.class).setBrightness(60);
        reg.getFirst(TemperatureControllable.class).setTemperatureC(24);

        Scannable scanner = reg.getFirst(Scannable.class);
        System.out.println("Attendance scanned: present=" + scanner.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (Powerable p : reg.getAll(Powerable.class)) {
            p.powerOff();
        }
    }
}
