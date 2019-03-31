/*
 *  Funciones para el manejo de Detalles
 */

$(document).ready(function () {

    $(document).on('change', '#id_articulo', function () {
        var idArticulo = $('#id_articulo').val();

        $.ajax({
            type: 'POST',
            url: "SArticulo?accion=damePrecio",
            data: {
                'idArticulo': idArticulo
            }
        })
                .done(function (data, textStatus, jqXHR) {
                    var precio = parseFloat(data * 1).toFixed(2);
                    var importe = parseFloat(data * 1).toFixed(2);
                    $('#precio').val(precio);
                    $('#importe').val(importe);
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    console.log(JSON.stringify(jqXHR));
                });
    });


    $(document).on('change', '#cantidad', function () {
        var cantidad = $('#cantidad').val();
        var precio = $('#precio').val();
        var importe = parseFloat(cantidad * precio).toFixed(2);
        precio = parseFloat(precio).toFixed(2);
        $("#precio").val(precio);
        $('#importe').val(importe);
    });

    $(document).on('change', '.edtCantidad', function () {
        var orden = $(this).data('orden');
        var cantidad = $("#cantidad" + orden).val();
        var precio = $("#precio" + orden).val();
        var importe = parseFloat(cantidad * precio).toFixed(2);
        precio = parseFloat(precio).toFixed(2);
        $("#precio" + orden).val(precio);
        $("#importe" + orden).val(importe);
    });

    $(document).on('change', '#precio', function () {
        var cantidad = $('#cantidad').val();
        var precio = $('#precio').val();
        var importe = parseFloat(cantidad * precio).toFixed(2);
        precio = parseFloat(precio).toFixed(2);
        $('#precio').val(precio);
        $('#importe').val(importe);
    });

    $(document).on('change', '.edtPrecio', function () {
        var orden = $(this).data('orden');
        var cantidad = $("#cantidad" + orden).val();
        var precio = $("#precio" + orden).val();
        var importe = (cantidad * precio).toFixed(2);
        precio = parseFloat(precio).toFixed(2);
        $("#precio" + orden).val(precio);
        $("#importe" + orden).val(importe);
    });

    $(document).on('click', '.btnEditar', function () {
        var numFactura = $(this).data('num_factura');
        var fechaFactura = $(this).data('fecha_factura');
        var orden = $(this).data('orden');
        var id_articulo = $('#id_articulo' + orden).val();
        var nombre = $('#nombre' + orden).val();
        var cantidad = $('#cantidad' + orden).val();
        var precio = $('#precio' + orden).val();

        $.ajax({
            type: 'POST',
            url: "SDetalle?accion=editar",
            data: {
                'numFactura': numFactura,
                'fechaFactura': fechaFactura,
                'orden': orden,
                'cantidad': cantidad,
                'precio': precio
            }
        })
                .done(function (data, textStatus, jqXHR) {
                    $('#linea' + orden).replaceWith("<tr id='linea" + orden + "'>"
                            + "<td>"
                            + "<input type='hidden' id='id_articulo" + orden + "' name='id_articulo" + orden + "' value='"+ id_articulo + "' />"
                            + "<input type='text' class='form-control' id='nombre" + orden + "' name='nombre" + orden + "' value='" + nombre + "' readonly='readonly' />"
                            + "</td>"
                            + "<td>"
                            + "<input class='numeros form-control' type='number' id='cantidad" + orden + "' name='cantidad" + orden + "' value='" + cantidad + "' />"
                            + "</td>"
                            + "<td>"
                            + "<input class='numeros form-control' type='number' id='precio" + orden + "' name='precio" + orden + "' value='" + parseFloat(precio).toFixed(2) + "' />"
                            + "</td>"
                            + "<td>"
                            + "<input type='text' class='numeros form-control' name='importe' value='" + parseFloat(cantidad * precio).toFixed(2) + "' readonly='readonly' />"
                            + "</td>"
                            + "<td class='acciones'>"
                            + "<a href='#' data-orden='" + orden + "' data-num_factura='" + numFactura + "' data-fecha_factura='" + fechaFactura + "' class='btn btn-primary btnEditar'  title='Editar Linea'>"
                            + "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>"
                            + "</a>"
                            + "</td>"
                            + "<td class='acciones'>"
                            + "<a href='#' data-orden='" + orden + "' data-num_factura='" + numFactura + "' data-fecha_factura='" + fechaFactura + "' class='btn btn-danger btnBorrar'  title='Borrar Linea'>"
                            + "<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>"
                            + "</a>"
                            + "</td>"
                            + "</tr>")
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    console.log(JSON.stringify(jqXHR));
                });
    });

    $(document).on('click', '.btnBorrar', function (e) {
        e.preventDefault();
        var num_factura = $(this).data('num_factura');
        var fecha_factura = $(this).data('fecha_factura');
        var num_orden = $(this).data('orden');

        if (confirm('¿Está seguro de querer eliminar la línea?')) {
            var row = $(this).parents('tr');
//            var id = row.data('id');

            $.ajax({
                type: 'POST',
                url: "SDetalle?accion=borrar",
                data: {
                    'numFactura': num_factura,
                    'fechaFactura': fecha_factura,
                    'orden': num_orden
                }
            })
                    .done(function (data, textStatus, jqXHR) {
                        row.fadeOut();
                    })
                    .fail(function (jqXHR, textStatus, errorThrown) {
                        console.log(JSON.stringify(jqXHR));
                    });
        }
    });
});