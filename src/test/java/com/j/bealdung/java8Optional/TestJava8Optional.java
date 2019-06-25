package com.j.bealdung.java8Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Test;

public class TestJava8Optional {

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

}
