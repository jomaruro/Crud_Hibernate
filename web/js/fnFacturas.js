/*
 *  Funciones para el manejo de Facturas
 */

$(document).ready(function () {

//    $(document).on('click', '.btnEditar', function() {
//        var num_factura = $(this).data('num_factura');
//        var fecha = $(this).data('fecha');
//        alert("Edita Factura -> " + num_factura + "\nFecha -> " + fecha);
	
//        var nombre = $('#nombre' + id_articulo).val();
//        var precio = $('#precio' + id_articulo).val();
        
//        $.ajax({
//          type: 'POST',
//          url: "SArticulo?accion=editar",
//          data: {
//            'id_articulo': id_articulo,
//            'nombre': nombre,
//            'precio': precio
//          }
//        })
//        .done(function(data, textStatus, jqXHR){
//        	$('#linea' + id_articulo).replaceWith("<tr id='linea" + id_articulo + "'>"
//        			+ "<td scope='row'>"
//        			+ "<input class='form-control' size='100' type='text' id='nombre" + id_articulo + "' name='nombre" + id_articulo + "' value='" + nombre + "' />"
//        			+ "</td>"
//        			+ "<td>"
//        			+ "<input class='form-control numeros' size='10' step='.01' type='number' id='precio" + id_articulo + "' name='precio" + id_articulo + "' value='" + precio + "' />"
//					+ "</td>"
//					+ "<td class='acciones'>"
//					+ "<a href='#' data-id='" + id_articulo + "' class='btn btn-primary btnEditar' title='Editar Artículo'>"
//					+ "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>"
//					+ "</a>"
//					+ "</td>"
//					+ "<td class='acciones'>"
//					+ "<a href='#' class='btn btn-danger' title='Eliminar Artículo'>"
//					+ "<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>"
//					+ "</a>"
//					+ "</td>"
//					+ "</tr>")
//        })
//        .fail(function(jqXHR, textStatus, errorThrown) {
//          console.log(JSON.stringify(jqXHR));
//        });
//      });
});