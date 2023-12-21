package project.maybedo.image;

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

    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void updateUrl(String newUrl) {
        this.url = newUrl;
    }
}