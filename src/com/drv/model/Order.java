package com.drv.model;

import java.util.List;

public record Order(Integer buyerId,
                    List<Product> products) {
}
