package com.exemple.forohub.repository;

import com.exemple.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

    public UserDetails findByCorreoElectronico(String username);
    
}
