package com.j.bealdung.java8Optional.service;

import com.j.bealdung.java8Optional.domain.Modem;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ModemService {

  public boolean priceIsInRangeWithoutOptional(Modem modem) {
    boolean isInRange = false;

    if (modem != null && modem.getPrice() != null
        && (modem.getPrice() >= 10
        && modem.getPrice() <= 15)) {

      isInRange = true;
    }
    return isInRange;
  }

  //The map call is simply used to transform a value to some other value.
  public boolean priceIsInRangeWithOptional(Modem modem) {
    return Optional.ofNullable(modem)
        //extract price out of modem object
        .map(Modem::getPrice)
        .filter(p -> p >= 10 && p <= 15)
        .isPresent();
  }
}
