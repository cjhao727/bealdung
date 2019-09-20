package com.j.bealdung.javaBackToBasic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String firstName;
  private String lastName;
  private Address address;
}
