package com.curso.ecommerce.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.pasarelaPaypal.webapp.source.payments.servlet.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/guardarOrden")

public class OrderController {

    static final String keyApi = "b2384410-f8d2-11e4-8bf3-77339302725b";

    @Autowired
    private ProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IOrdenService ordensService;
    private Logger logg= LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String showProductos(Model model) {

        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        return "usuario/ordenesPaypal";
    }

    @GetMapping("/ventas")
    public String generarVentaUsuarios(Model model , String keyPaypal, Z2SaveCreditCard tarjeta) {
        model.addAttribute("usuarios"+keyPaypal + "Numero Tarjeta" +tarjeta.getNumero(), usuarioService.findAll());
        return "usuario/generarVenta";
    }

    @GetMapping("/ordenes")
    public String ordenesUsuario(Model model) {
        model.addAttribute("ordenes", ordensService.findAll());
        return "usuario/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalleOrdenUsuario(Model model, @PathVariable Integer id) {
        logg.info("Id de la orden {}",id);
        Orden orden= ordensService.findById(id).get();

        model.addAttribute("detalles", orden.getDetalle());

        return "usuario/detalleorden";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion( HttpSession session ) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }

}