package project.maybedo.image;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import project.maybedo.member.Member;

import javax.persistence.*;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    private String originFileName;

    private String url;

    public void updateUrl(String newUrl) {
        this.url = newUrl;
    }
}
