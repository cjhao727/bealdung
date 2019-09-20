package com.j.bealdung.javaBackToBasic;

import static org.assertj.core.api.Assertions.assertThat;

import com.j.bealdung.javaBackToBasic.domain.Address;
import com.j.bealdung.javaBackToBasic.domain.User;
import org.junit.Test;

public class BackToBasicTest {

  @Test
  public void whenShallowCopying_thenObjectsShouldNotBeSame() {
    Address address = new Address("Downing St 10", "London", "England");
    User pm = new User("Prime", "Minister", address);

    User shallowCopy = new User(pm.getFirstName(), pm.getLastName(), pm.getAddress());

    assertThat(shallowCopy).isNotSameAs(pm);
  }

}
