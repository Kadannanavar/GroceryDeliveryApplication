package com.grocery.service;

import java.util.List;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Product;
import com.grocery.model.Tag;

public interface TagService {

	Tag addTag(Tag tag) throws ResourceNotFoundException;

	Tag findTagById(long id) throws ResourceNotFoundException;

	List<Tag> findAllTags() throws ResourceNotFoundException;

	void deleteTag(long id) throws ResourceNotFoundException;

	void addTagToProduct(long idProduct, long idTag);

	List<Tag> findTagsForProduct(long idProduct);

	void deleteTagFromProduct(long idTag, long idProduct);

	List<Product> findProductsForTag(long idTag);

}
