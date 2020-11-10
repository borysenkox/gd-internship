USE outfit_shop_db;
	START TRANSACTION;

	INSERT INTO category(id, name) VALUES (1, 'All');
	INSERT INTO category(id, name, parent_id) VALUES (2, 'Men', 1), (3, 'Women', 1);
	INSERT INTO category(id, name, parent_id) VALUES (4, 'Hoodies', 2), (5, 'Hoodies', 3), (6, 'Pants', 2), (7, 'Pants', 3), (8, 'Socks', 2), (9, 'Socks', 3);

	INSERT INTO product(id, name, price, description, brand, image) VALUES (1, "Under Armour Men's Rival Fleece Logo Hoodie",
	'1273.90', "A classic active or casual look, this cozy fleece from Under Armour is updated with
	 moisture-wicking technology for comfort on the go.", "Under Armor", "https://slimages.macysassets.com/is/image/MCY/products/6/optimized/14653706_fpx.tif?op_sharpen=1&wid=1230&hei=1500&fit=fit,1&$filterxlrg$");
		 
	INSERT INTO product(id, name, price, description, brand, image) VALUES (2, "Under Armour Men's Tricot Jogger Pants",
	'890.20', "A classic active or casual look, these Under Armour bottoms will bring you comfort on the go.",
	"Under Armor", "https://slimages.macys.com/is/image/MCY/products/3/optimized/16330633_fpx.tif?op_sharpen=1&wid=1230&hei=1500&fit=fit,1&$filterxlrg$");
		 
	INSERT INTO product(id, name, price, description, brand, image) VALUES (3, "Under Armour Women's 6-Pk. Essential No-Show Socks",
	'616.13', "Stock up on comfort with this six pack of socks from Under Armour, in a no-show rise with stretch fabric.",
	"Under Armor", "https://slimages.macys.com/is/image/MCY/products/4/optimized/10189354_fpx.tif?op_sharpen=1&wid=1230&hei=1500&fit=fit,1&$filterxlrg$");
	 
	INSERT INTO product_category(product_id, category_id) VALUES (1, 4), (2, 6), (3, 9);
	 
	INSERT INTO inventory(id, product_id, size, quantity) VALUES (1, 1, 'M', 24), (2, 1, 'L', 18), (3, 1, 'S', 6),
	(4, 2, 'XS', 3), (5, 2, 'M', 19), (6, 2, 'XL', 12), (7, 3, '34-36', 7), (8, 3, '36-38', 20), (9, 3, '38-40', 12);
	 
	INSERT INTO review(id, rate, filling, product_id, parent_id, client_name) VALUES(1, 4, 'Pretty cool pants, I liked it! But size is too small for me, so 4 stars.',
	2, NULL, 'Mike'), (2, 5, 'The quality of this hoodie is brilliant, color the same as on the website, a bit oversize. RECOMMEND!', 1, NULL, 'Tom');
	 
	INSERT INTO review(id, rate, filling, product_id, parent_id, client_name) VALUES (3, NULL, 'I think it is your fault that you bought pants with wrong size!', 2, 1, DEFAULT);
 
	INSERT INTO sale_event(id, start_time, end_time, sale_percent, category_id) VALUES (1, '2020.11.11 00:00:00', '2020.11.12 00:00:00', 37, 1),
    (2, '2020.11.12 00:01:00', '2020.11.30 23:59:00', 5, 1); 
    
    INSERT INTO address(id, country, city, street, num_house, num_flat, postal_code) VALUES
    (1, 'Ukraine', 'Kharkiv', 'Prospekt Nauky', 14, NULL, 61166),
    (2, 'Ukraine', 'Kyiv', 'Shevchenka', 23, 54, 85313);
    
    INSERT INTO client(id, first_name, last_name, email, phone, address_id) VALUES
    (1, 'Petro', 'Petrov', 'p.petrov@gmail.com', '380992384137', 1),
    (2, 'Vasily', 'Smirnov', 'v.sss121@gmail.com', '380664421740', 2);
    
    INSERT INTO ordering(id, client_id, sum_price) VALUES 
    (1, 1, 1273.90), (2, 2, 1506.33);
    
    INSERT INTO ordering_inventory(ordering_id, inventory_id) VALUES (1, 1), (2, 5), (2, 9);
    
    COMMIT;
    


