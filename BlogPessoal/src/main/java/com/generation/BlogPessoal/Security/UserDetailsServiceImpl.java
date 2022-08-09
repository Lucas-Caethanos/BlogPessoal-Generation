package com.generation.BlogPessoal.Security;

import com.generation.BlogPessoal.Model.Usuario;
import com.generation.BlogPessoal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
        usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " não encontrado."));
        return usuario.map(UserDetailsImpl::new).get();
    }
}
