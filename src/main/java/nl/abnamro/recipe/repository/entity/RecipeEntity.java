package nl.abnamro.recipe.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="RECIPE")
public class RecipeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="IS_VEGETARIAN")
    private boolean isVegetarian;

    @OneToMany(targetEntity = IngredientEntity.class,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="RECIPE_ID",referencedColumnName = "id")
    private List<IngredientEntity> ingredients;

    @Column(name="INSTRUCTIONS")
    private String instructions;

    @Column(name="NO_OF_SERVINGS")
    private int servings;
}
