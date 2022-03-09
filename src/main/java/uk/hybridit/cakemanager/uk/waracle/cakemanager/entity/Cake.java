package uk.hybridit.cakemanager.uk.waracle.cakemanager.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "CAKE")
public class Cake {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=2, message="Name should be atleast 2 characters")
    private String name;
    @NotNull
    @Size(min=2, max = 255, message="Description should be min 2 and max 255")
    private String description;
    @NotNull
    @Size(min=2, max = 255, message="Image URL should be min 2 and max 255")
    private String imageURL;

}
