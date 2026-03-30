import java.util.*;

public class DeviceRegistry {

    private List<Object> devices = new ArrayList<>();

    public void add(Object d) {
        devices.add(d);
    }

    public <T> List<T> getByCapability(Class<T> capability) {
        List<T> result = new ArrayList<>();

        for (Object d : devices) {
            if (capability.isInstance(d)) {
                result.add(capability.cast(d));
            }
        }

        return result;
    }
}