package com.yellow.pepper.api.controller.accounts.create;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Create account response
 */
@AllArgsConstructor
@Getter
@Builder
@ApiModel(value = "Create account response")
public class CreateAccountResponse {
  private String status;
}
