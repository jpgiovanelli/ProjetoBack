package br.com.projback.projetoback.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import br.com.projback.projetoback.model.Profile;

@Data
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany
    private List<Profile> profiles = new ArrayList<>();

}
