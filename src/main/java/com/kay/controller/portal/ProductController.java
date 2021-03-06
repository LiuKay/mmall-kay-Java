package com.kay.controller.portal;

import com.github.pagehelper.PageInfo;
import com.kay.service.ProductService;
import com.kay.vo.ProductDetailVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kay on 2018/3/22.
 * 商品前台
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ProductDetailVo getProductList(@PathVariable Integer productId) {
        return productService.getProductList(productId);
    }

    /**
     * 产品搜索及动态排序List
     *
     * @return
     */
    @GetMapping("/list")
    public PageInfo getProductList(
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return productService.getProductByKeywordCategory(categoryId, keyword, pageNum, pageSize, orderBy);
    }

    @GetMapping(value = "/{categoryId}/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    public PageInfo getProductListRESTful(@PathVariable(value = "categoryId") Integer categoryId,
                                          @PathVariable(value = "keyword") String keyword,
                                          @PathVariable(value = "pageNum") Integer pageNum,
                                          @PathVariable(value = "pageSize") Integer pageSize,
                                          @PathVariable(value = "orderBy") String orderBy) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }
        return productService.getProductByKeywordCategory(categoryId, keyword, pageNum, pageSize, orderBy);
    }

    //http://localhost:8080/products/keyword/手机/1/10/price_asc/
    @GetMapping(value = "/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    public PageInfo listRESTful(@PathVariable(value = "keyword") String keyword,
                                @PathVariable(value = "pageNum") Integer pageNum,
                                @PathVariable(value = "pageSize") Integer pageSize,
                                @PathVariable(value = "orderBy") String orderBy) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }

        return productService.getProductByKeywordCategory(null, keyword, pageNum, pageSize, orderBy);
    }

    //http://localhost:8080/products/category/100012/1/10/price_asc/
    @GetMapping(value = "/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    public PageInfo listRESTful(@PathVariable(value = "categoryId") Integer categoryId,
                                @PathVariable(value = "pageNum") Integer pageNum,
                                @PathVariable(value = "pageSize") Integer pageSize,
                                @PathVariable(value = "orderBy") String orderBy) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(orderBy)) {
            orderBy = "price_asc";
        }

        return productService.getProductByKeywordCategory(categoryId, null, pageNum, pageSize, orderBy);
    }
}
