/*
 *  Funciones para el manejo de Clientes
 */

$(document).ready(function () {

    $(document).on('click', '.btnEditar', function () {
        var id_cliente = $(this).data('id');
        var nombre = $('#nombre' + id_cliente).val();
        var cif = $('#cif' + id_cliente).val();
        var direccion = $('#direccion' + id_cliente).val();
        var localidad = $('#localidad' + id_cliente).val();
        var provincia = $('#provincia' + id_cliente).val();

        $.ajax({
            type: 'POST',
            url: "SCliente?accion=editar",
            data: {
                'id_cliente': id_cliente,
                'nombre': nombre,
                'cif': cif,
                'direccion': direccion,
                'localidad': localidad,
                'provincia': provincia
            }
        })
                .done(function (data, textStatus, jqXHR) {
                    $('#linea' + id_cliente).replaceWith("<tr id='linea" + id_cliente + "'>"
                            + "<th scope='row'>"
                            + "<input class='form-control' type='text' id='nombre " + id_cliente + "' name='nombre" + id_cliente + "' value='" + nombre + "' />"
                            + "</th>"
                            + "<td>"
                            + "<input class='form-control' type='text' id='cif" + id_cliente + "' name='cif" + id_cliente + "' value='" + cif + "' />"
                            + "</td>"
                            + "<td>"
                            + "<input class='form-control' type='text' id='direccion" + id_cliente + "' name='direccion" + id_cliente + "' value='" + direccion + "' />"
                            + "</td>"
                            + "<td>"
                            + "<input class='form-control' type='text' id='localidad" + id_cliente + "' name='localidad" + id_cliente + "' value='" + localidad + "' />"
                            + "</td>"
                            + "<td>"
                            + "<input class='form-control' type='text' id='provincia" + id_cliente + "' name='provincia" + id_cliente + "' value='" + provincia + "' />"
                            + "</td>"
                            + "<td class='acciones'>"
                            + "<a href='#' class='btn btn-primary btnEditar'>"
                            + "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>"
                            + "</a>"
                            + "</td>"
                            + "<td class='acciones'>"
                            + "<a href='#' class='btn btn-danger'>"
                            + "<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>"
                            + "</a>"
                            + "</td>"
                            + "</tr>")
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    console.log(JSON.stringify(jqXHR));
                });
    });
});