package com.sema4.foudastore.dto;

import com.sema4.foudastore.entities.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Status status;
}
