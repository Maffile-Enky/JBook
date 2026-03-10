package gitee.jettbook.jbook.mapper;

import gitee.jettbook.jbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringBootMapper extends JpaRepository<Book, Integer> {
    
    // 根据书名查找书籍
    List<Book> findByBookName(String bookName);
    
    // 根据价格范围查找书籍
    List<Book> findByBookPriceBetween(String minPrice, String maxPrice);
    
    // 自定义查询：根据书名模糊查询
    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:name%")
    List<Book> findByBookNameContaining(@Param("name") String name);
    
    // 检查书籍是否存在
    boolean existsByBookId(Integer bookId);
}
