package pl.sunflux.parking;

public interface ParkingResponseInterface<T, P> {
    public T prepareResponse(P container);
}
