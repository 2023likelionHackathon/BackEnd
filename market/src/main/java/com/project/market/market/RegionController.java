package com.project.market.market;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/region")
public class RegionController {
   private final RegionRepository regionRepository;


}
