package com.msvc.cliente.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-boletas", url = "http://localhost:8001")
public class BoletaClientRest {
}
