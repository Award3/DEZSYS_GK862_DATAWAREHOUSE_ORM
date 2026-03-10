package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/warehouse") // URL angepasst auf /warehouse
public class MainController {

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Autowired
	private ProductRepository productRepository;

	// Speichert ein komplettes Warehouse inkl. Produkten
	@PostMapping(path="/add")
	public @ResponseBody String addNewWarehouse (@RequestBody Warehouse warehouse) {
		// Da wir CascadeType.ALL nutzen, müssen wir sicherstellen,
		// dass jedes Produkt auch sein Warehouse kennt
		if (warehouse.getProductData() != null) {
			for (Product p : warehouse.getProductData()) {
				p.setWarehouse(warehouse);
			}
		}
		warehouseRepository.save(warehouse);
		return "Warehouse and Products saved!";
	}

	// Listet alle Warenhäuser und deren Produkte auf
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Warehouse> getAllWarehouses() {
		return warehouseRepository.findAll();
	}
}