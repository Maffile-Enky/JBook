package gitee.jettbook.jbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "欢迎来到JBook图书管理系统！\n\n可用API端点：\n" +
               "• GET /api/books/health - 健康检查\n" +
               "• GET /api/books - 获取所有图书\n" +
               "• GET /api/books/{id} - 根据ID获取图书\n" +
               "• POST /api/books - 创建新图书\n" +
               "• PUT /api/books/{id} - 更新图书信息\n" +
               "• DELETE /api/books/{id} - 删除图书\n" +
               "• GET /api/books/search?bookName={name} - 按书名搜索\n\n" +
               "详细API文档请参考项目目录下的 API文档.md 文件。";
    }
}