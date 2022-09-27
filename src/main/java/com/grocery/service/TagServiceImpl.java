package com.grocery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grocery.dao.ProductDao;
import com.grocery.dao.TagDao;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Product;
import com.grocery.model.Tag;

@Service
@Transactional
@Component

public class TagServiceImpl implements TagService {

	@Autowired
	private TagDao tagDao;

	@Autowired
	private ProductDao productDao;

	@Override
	public Tag addTag(Tag tag)throws ResourceNotFoundException {
		return tagDao.save(tag);
	}

	@Override
	public Tag findTagById(long id) throws ResourceNotFoundException {

		if (tagDao.findById(id).isPresent()) {
			Tag tag = tagDao.findById(id).get();
			return tag;
		} else {
			throw new ResourceNotFoundException("Tag with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public List<Tag> findAllTags() throws ResourceNotFoundException {
		List<Tag> tag = (List<Tag>) tagDao.findAll();
		if (tag == null)
			throw new ResourceNotFoundException("Tag not created");
		return tag;
	}

	@Override
	public void deleteTag(long id) throws ResourceNotFoundException {

		if (tagDao.findById(id).isPresent()) {
			tagDao.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Tag with Id: " + id + " doesn't exist!!");
		}
	}

	@Override
	public void addTagToProduct(long idProduct, long idTag) {
		Product product = productDao.findById(idProduct).orElse(null);
		Tag tag = tagDao.findById(idTag).orElse(null);
		tag.addProductToTag(product);
		product.addTag(tag);

	}

	@Override
	public List<Tag> findTagsForProduct(long idProduct) {
		Product product = productDao.findById(idProduct).orElse(null);
		return product.getTags();
	}

	@Override
	public void deleteTagFromProduct(long idTag, long idProduct) {
		Product product = productDao.findById(idProduct).orElse(null);
		Tag tag = tagDao.findById(idTag).orElse(null);
		product.getTags().remove(tag);

	}

	@Override
	public List<Product> findProductsForTag(long idTag) {
		Tag tag = tagDao.findById(idTag).orElse(null);
		return tag.getProducts();
	}
}
