package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public String obtenerUsuarios(Model model) {
        model.addAttribute("usuarioModel",usuarioService.obtenerUsuarios());
        return "usuarios";
    }


    @PostMapping()
    public UsuarioModel guardarUsuario(@ModelAttribute UsuarioModel usuarioModel) {
        return this.usuarioService.guardarUsuario(usuarioModel);
    }

    @PutMapping(path = "/{id}")
    public UsuarioModel update(@PathVariable("id") Long id, @RequestBody UsuarioModel usuarioModel) {
        if (usuarioModel.getId() != null) {
            return usuarioService.save(usuarioModel);

        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }


    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }

}