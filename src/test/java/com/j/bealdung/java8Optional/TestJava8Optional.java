package com.j.bealdung.java8Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.j.bealdung.java8Optional.domain.Modem;
import com.j.bealdung.java8Optional.service.ModemService;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class TestJava8Optional {

  @InjectMocks
  private ModemService modemService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void whenCreatesEmptyOptional_thenCorrect() {
    Optional<String> empty = Optional.empty();
    assertFalse(empty.isPresent());
  }

  @Test
  public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
    String name = "real";
    Optional<String> optionalS = Optional.of(name);
    assertTrue(optionalS.isPresent());
  }

  @Test(expected = NullPointerException.class)
  public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
    String name = null;
    Optional.of(name);
  }

  @Test
  public void givenNonNull_whenCreatesNullable_thenCorrect() {
    String name = "real";
    Optional<String> optionalS = Optional.ofNullable(name);
    assertTrue(optionalS.isPresent());
  }

  @Test
  public void givenNull_whenCreatesNullable_thenCorrect() {
    String name = null;
    Optional<String> optionalS = Optional.ofNullable(name);
    assertFalse(optionalS.isPresent());
  }

  @Test
  public void givenOptional_whenIsPresentWorks_thenCorrect() {
    Optional<String> opt = Optional.of("Baeldung");
    assertTrue(opt.isPresent());

    opt = Optional.ofNullable(null);
    assertFalse(opt.isPresent());
  }

  @Test
  public void givenOptional_whenIfPresentWorks_thenCorrect() {
    Optional<String> optionalS = Optional.of("bealdung");
    optionalS.ifPresent(name -> System.out.println(name.length()));
  }

  @Test
  public void whenOrElseWorks_thenCorrect() {
    String nullName = null;
    String name = Optional.ofNullable(nullName).orElse("huskar");
    assertEquals("huskar", name);
  }

  @Test
  public void givenNullText_whenOrElseGetAndOrElseOverlap_thenCorrect() {
    String text = null;
    String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
    assertEquals("this is default value", defaultText);

    defaultText = Optional.ofNullable(text).orElse(getMyDefault());
    assertEquals("this is default value", defaultText);
  }

  @Test
  public void givenNonNullText_whenOrElseGetAndOrElseOverlap_thenCorrect() {
    String text = "text present";

    System.out.println("using orElseGet:");
    String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);

    assertEquals("text present", defaultText);

    System.out.println("using orElse:");
    defaultText = Optional.ofNullable(text).orElse(getMyDefault());
    assertEquals("text present", defaultText);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenOrElseThrowWorks_thenCorrect() {
    String nullName = null;
    String name = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
    assertNotNull(name);
  }

  @Test
  public void givenOptional_whenGetsValue_thenCorrect() {
    Optional<String> opt = Optional.of("baeldung");
    String name = opt.get();
    assertEquals("baeldung", name);
  }

  @Test(expected = NoSuchElementException.class)
  public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
    Optional<String> opt = Optional.ofNullable(null);
    String name = opt.get();
    assertNotNull(name);
  }

  @Test
  public void whenOptionalFilterWorks_thenCorrect() {
    Integer year = 2019;
    Optional<Integer> yearOptional = Optional.of(year);
    boolean is2019 = yearOptional.filter(y -> y == 2019).isPresent();
    assertTrue(is2019);
    boolean is2018 = yearOptional.filter(y -> y == 2018).isPresent();
    assertFalse(is2018);
  }

  @Test
  public void whenFiltersWithoutOptional_thenCorrect() {
    assertTrue(modemService.priceIsInRangeWithoutOptional(new Modem(10.0)));
    assertFalse(modemService.priceIsInRangeWithoutOptional(new Modem(9.9)));
    assertFalse(modemService.priceIsInRangeWithoutOptional(new Modem(null)));
    assertFalse(modemService.priceIsInRangeWithoutOptional(new Modem(15.5)));
    assertFalse(modemService.priceIsInRangeWithoutOptional(null));

    //The map call is simply used to transform a value to some other value.
    assertTrue(modemService.priceIsInRangeWithOptional(new Modem(10.0)));
    assertFalse(modemService.priceIsInRangeWithOptional(new Modem(9.9)));
    assertFalse(modemService.priceIsInRangeWithOptional(new Modem(null)));
    assertFalse(modemService.priceIsInRangeWithOptional(new Modem(15.5)));
    assertFalse(modemService.priceIsInRangeWithOptional(null));
  }

  private String getMyDefault() {
    System.out.println("Getting Default Value");
    return "this is default value";
  }
}
