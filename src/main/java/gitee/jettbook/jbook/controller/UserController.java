package gitee.jettbook.jbook.controller;

import gitee.jettbook.jbook.entity.Book;
import gitee.jettbook.jbook.mapper.SpringBootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class UserController {

    @Autowired
    private SpringBootMapper bookRepository;

    // 获取所有书籍
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 根据ID获取书籍
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // 创建新书籍
    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // 更新书籍
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        if (!bookRepository.existsByBookId(id)) {
            return ResponseEntity.notFound().build();
        }
        bookDetails.setBookId(id);
        Book updatedBook = bookRepository.save(bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    // 删除书籍
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 根据书名搜索
    @GetMapping("/search")
    public List<Book> searchBooksByName(@RequestParam String name) {
        return bookRepository.findByBookNameContaining(name);
    }

    // 健康检查接口
    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running!";
    }
}
