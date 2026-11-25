CREATE DATABASE db_library_management;
USE db_library_management;

CREATE TABLE borrow_cards (
cards_id SERIAL PRIMARY KEY AUTO_INCREMENT, -- ma phieu muon
book_title VARCHAR(150) NOT NULL, -- ten sach muon
borrower_name VARCHAR(100) NOT NULL, -- ten doc gia muon
borrow_date TIMESTAMP NOT NULL, -- ngay muon sach
return_deadline TIMESTAMP NOT NULL, -- han tra sach
quantity INT NOT NULL, -- so luong muon
status VARCHAR(30) NOT NULL -- trang thai(borrowing, returned, overdue)
);

DELIMITER //
CREATE PROCEDURE  get_all_cards()
BEGIN SELECT * FROM borrow_cards; -- dan ve bang phieu muon de lay danh sach
END //

-- them phieu muon(do dai kieu du lieu bang voi bang borrow_card - co bac buoc hay khong se hoi thay sau)
CREATE PROCEDURE add_cards (in_title VARCHAR(150), in_name VARCHAR(100), in_borrow TIMESTAMP, in_dealine TIMESTAMP, in_quantity INT, in_status VARCHAR(30))
BEGIN
INSERT INTO bowwor_cards(book_tittle, borrower_name, borrow_date, return_deadline, quantity, status)
VALUE (in_tittle, in_name, in_borrow, in_deadline, in_quantity, in_status);
END //

-- tim theo ten doc gia
CREATE PROCEDURE find_by_borrowe_name(in_borrower_name VARCHAR(100))
BEGIN
SELECT * FROM borrow_cards WHERE borrowe_name = in_borrower_name;
END //

-- cap nhat theo card id (dat bien moi la in_+ten cot thu neu sai khi chay thu se sua lai0
CREATE PROCEDURE update_card_id(in_card_id SERIAL, in_book_title VARCHAR(150), in_borrower_name VARCHAR(100), in_borrow_date TIMESTAMP, in_return_deadline TIMESTAMP, in_quantity INT, in_status VARCHAR(30))
BEGIN
UPDATE bowwor_card
SET book_title = in_book_title, borrower_name = in_borrower_name, borrow_date = in_borrow_date, return_deadline = in_return_deadline, quantity = in_quantity, status = in_status
WHERE card_id = in_card_id;
END //

-- tim theo sach
-- thoat

USE db_library_management;

DELIMITER //

DROP PROCEDURE IF EXISTS add_cards //
CREATE PROCEDURE add_cards (in_title VARCHAR(150), in_name VARCHAR(100), in_borrow TIMESTAMP, in_dealine TIMESTAMP, in_quantity INT, in_status VARCHAR(30))
BEGIN
    INSERT INTO borrow_cards(book_title, borrower_name, borrow_date, return_deadline, quantity, status)
    VALUES (in_title, in_name, in_borrow, in_dealine, in_quantity, in_status);
END //

DROP PROCEDURE IF EXISTS find_by_borrowe_name //
CREATE PROCEDURE find_by_borrowe_name(in_borrower_name VARCHAR(100))
BEGIN
    SELECT * FROM borrow_cards WHERE borrower_name = in_borrower_name;
END //

DROP PROCEDURE IF EXISTS update_card_id //
CREATE PROCEDURE update_card_id(in_card_id SERIAL, in_book_title VARCHAR(150), in_borrower_name VARCHAR(100), in_borrow_date TIMESTAMP, in_return_deadline TIMESTAMP, in_quantity INT, in_status VARCHAR(30))
BEGIN
    UPDATE borrow_cards
    SET book_title = in_book_title, borrower_name = in_borrower_name, borrow_date = in_borrow_date, return_deadline = in_return_deadline, quantity = in_quantity, status = in_status
    WHERE cards_id = in_card_id;
END //

DELIMITER ;




