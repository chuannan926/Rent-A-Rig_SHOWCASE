package cova.rar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cova.rar.dao.ProductDao;
import cova.rar.entities.Product;
import cova.rar.entities.RequestedInventory;

public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	public List<Product> getProducts(String filter) {
		
		switch(filter) {
			case "all": return productDao.getAll();
			case "gamingrigs": return productDao.getCategory("gamingrigs");
			case "portable": return productDao.getCategory("portable");
			case "accessories": return productDao.getCategory("accessories");
			default: return productDao.getSearch(filter);
		}

	}

	public Product getProduct(String prodID) {
		return productDao.getProduct(prodID);
	}

	public List<Product> getTabProducts(String filter, int i) {
		
		List<Product> tabProducts = new ArrayList<Product>();
		List<Product> allProducts = productDao.getCategory(filter);
		
		// magic filters values should be replaced
		final String[][] filters = {{"desktop", "thinandlight"},{"monitor","gaminglaptop"},{"accessories","tablet"}};
		
		for (Product product : allProducts) {
			if (product.getCategory().contains(filters[i-1][0]) || product.getCategory().contains(filters[i-1][1])) {
				tabProducts.add(product);
			}
		}
		
		return tabProducts;
	}

	public boolean updateProductInventory(String product_id, int request_qty) {

		return productDao.updateProductInventory(product_id, request_qty);
	}

}
