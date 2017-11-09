package com.schibstedspain.leku.geocoder;

import android.location.Address;
import android.location.Geocoder;
import com.google.android.gms.maps.model.LatLng;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.List;

public class AndroidGeocoderDataSource implements GeocoderInteractorDataSource {

  private final Geocoder geocoder;
  private final static int MAX_RESULTS = 5;

  public AndroidGeocoderDataSource(Geocoder geocoder) {
    this.geocoder = geocoder;
  }

  @Override
  public Observable<List<Address>> getFromLocationName(String query) {
    return Observable.create(subscriber -> {
      try {
        subscriber.onNext(geocoder.getFromLocationName(query, MAX_RESULTS));
        subscriber.onComplete();
      } catch (IOException e) {
        subscriber.onError(e);
      }
    });
  }

  @Override
  public Observable<List<Address>> getFromLocationName(String query, LatLng lowerLeft,
      LatLng upperRight) {
    return Observable.create(subscriber -> {
      try {
        subscriber.onNext(geocoder.getFromLocationName(query, MAX_RESULTS, lowerLeft.latitude,
            lowerLeft.longitude, upperRight.latitude, upperRight.longitude));
        subscriber.onComplete();
      } catch (IOException e) {
        subscriber.onError(e);
      }
    });
  }

  @Override
  public Observable<List<Address>> getFromLocation(double latitude, double longitude) {
    return Observable.create(subscriber -> {
      try {
        subscriber.onNext(geocoder.getFromLocation(latitude, longitude, MAX_RESULTS));
        subscriber.onComplete();
      } catch (IOException e) {
        subscriber.onError(e);
      }
    });
  }
}
