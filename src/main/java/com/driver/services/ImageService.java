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
        image.setDimensions(dimensions);
        image.setBlog(blog); // setting foreign key attr

//        List<Image> currentImagesOfBlog = blog.getImageList();
//        currentImagesOfBlog.add(image);
        blog.getImageList().add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int l=Integer.MIN_VALUE;
        int b=Integer.MIN_VALUE;
        StringBuilder len=new StringBuilder();
        StringBuilder br=new StringBuilder();

        int a=0;
        while(a<screenDimensions.length()){
            if(screenDimensions.charAt(a)=='X') break;
            len.append(screenDimensions.charAt(a));
            a++;
        }
        a++;
        while(a<screenDimensions.length()){
            br.append(screenDimensions.charAt(a));
            a++;
        }

        l=Integer.parseInt(len.toString());
        b=Integer.parseInt(br.toString());

        len=new StringBuilder();
        br=new StringBuilder();

        int l1=Integer.MIN_VALUE;
        int b1=Integer.MAX_VALUE;

        a=0;
        Image image=imageRepository2.findById(id).get();
        String t=image.getDimensions();
        while(a<t.length()){
            if(t.charAt(a)=='X') break;
            len.append(t.charAt(a));
            a++;
        }
        a++;
        while(a<t.length()){
            br.append(t.charAt(a));
            a++;
        }

        l1=Integer.parseInt(len.toString());
        b1=Integer.parseInt(br.toString());

        int t1=l/l1;
        int t2=b/b1;
        return t1*t2;
    }

}
