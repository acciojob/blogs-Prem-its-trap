package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimension(dimensions);
        image.setBlog(blog); // setting foreign key attr

        List<Image> currentImagesOfBlog = blog.getImageList();
        currentImagesOfBlog.add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] scArray = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimension = image.getDimension();
        String[] imgArray = imageDimension.split("X");

        int scr1 = Integer.parseInt(scArray[0]);
        int scr2 = Integer.parseInt(scArray[1]);

        int img1 = Integer.parseInt(imgArray[0]);
        int img2 = Integer.parseInt(imgArray[1]);

        return no_Images(scr1,scr2,img1,img2);
    }
    private int no_Images(int scr1, int scr2, int img1, int img2){
        int lenC = scr1/img1;
        int lenB = scr2/img2;
        return lenC*lenB;
    }
}
