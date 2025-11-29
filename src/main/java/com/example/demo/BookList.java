package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class BookList {
    List<Book> book_list = new ArrayList<>();
    public BookList() {
        book_list.add(new Book(1,"Harry Porter"));
        book_list.add(new Book(2,"Manjadi hee"));
    }

    @GetMapping("/getBooks")
    public List<Book> getAllBooks(){
        return book_list;
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        for(int i=0;i<book_list.size();i++){
            Book m = book_list.get(i);
            if(m.bookId == id){
                return ResponseEntity.ok(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PostMapping("/setBooks")
    public Book createNewBook(@RequestBody Book newBook){
        book_list.add(newBook);
        return newBook;
    }

    @PutMapping("/updateBook/{id}")
    public Book updateBook (@PathVariable int id, @RequestBody String name){
        for(int i=0;i<book_list.size();i++){
            Book m = book_list.get(i);
            if(m.bookId == id){
                m.bookName = name;
                return m;
            }
        }
        return null;
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable int id){
        for(int i=0;i<book_list.size();i++){
            Book m = book_list.get(i);
            if(m.bookId == id){
                book_list.remove(m);
                return ResponseEntity.ok(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
