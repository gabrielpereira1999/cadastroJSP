<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bean.ClientBean" %>
<html>
	<%@ include file="header.jsp"%>

    
   	<c:if test="${client != null && !disabled}">
   		<h1>Editar cadastro</h1>
 	</c:if>
 	<c:if test="${client != null && disabled}">
   		<h1>Visualizar cadastro</h1>
 	</c:if>
	<c:if test="${client == null}">
		<h1>Cadastro do Cliente</h1>
	</c:if>
	
	
	<c:if test="${client != null}">
		<form action="update" method="post" id="form">
    </c:if>
    <c:if test="${client == null}">
		<form action="insert" method="post" id="form">
    </c:if>
		<div class="form-group row">
			<label for="name" class="col-sm-2 col-form-label">Nome</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name" name="name" required value="<c:out value='${client.nameClient}' />">
			</div>
		</div>
		<div class="form-group row">
			<label for="cpf" class="col-sm-2 col-form-label">CPF</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="cpf" name="cpf" required <c:if test="${client != null}">disabled</c:if> value="<c:out value='${client.cpfClient}' />">
			</div>
		</div>
		<div class="form-group row">
			<label for="state" class="col-sm-2 col-form-label">Estado</label>
			<div class="col-sm-4">
				<select class="form-control" name="state" id="state" required>
				</select>
			</div>
			<label for="city" class="col-sm-2 col-form-label">Cidade</label>
			<div class="col-sm-4">
				<select class="form-control" name="city" id="city" required>
				</select>
			</div>
		</div>
		<div class="form-group row">
			<label for="address" class="col-sm-2 col-form-label">Endereço</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="address" name="address" required value="<c:out value='${client.addressClient}' />">
			</div>
		</div>
		<input type="hidden" id="telephoneParameter" name="telephoneParameter"/>
		<c:forEach var="telephone" items="${client.telephonesClient}">
			<div class="form-group row">
				<label for="telephone" class="col-sm-2 col-form-label">Telefone</label>
				<div class="col-sm-10">
					<input type="text" class="form-control telephone" id="telephone" name="telehpone" value="<c:out value='${telephone}' />">
				</div>
			</div>
		</c:forEach>

	</form>

	<div class="row">
		<div class="col">
			<button type="button" class="btn btn-outline-primary mr-3" onclick="addTelephone()">Adicionar telefone</button>
			
			<c:if test="${client != null}">
				<button type=submit form="form" class="btn btn-primary" onclick="populateTelephonesArray()">Atualizar</button>
			</c:if>
			<c:if test="${client == null}">
				<button type="submit" form="form" class="btn btn-primary" onclick="populateTelephonesArray()">Cadastrar</button>
			</c:if>
		</div>
	</div>

	<script type="text/javascript">
		function addTelephone() {
			var telephoneInput = document.createElement("div");
			telephoneInput.innerHTML = document.getElementById("telephoneBlock").innerHTML;
			document.getElementById("form").appendChild(telephoneInput);
			$(".telephone").inputmask({ mask: ["(99) 9999-9999", "(99) 99999-9999", ], keepStatic: true });
		}
	</script>
	<script id="telephoneBlock" type="text/html">
		<div class="form-group row">
			<label for="telephone" class="col-sm-2 col-form-label">Telefone</label>
			<div class="col-sm-10">
				<input type="text" class="form-control telephone" id="telephone" name="telephone">
			</div>
		</div>
	</script>
	<script type="text/javascript">
		$(document).ready(function () {
			$.getJSON('http://mendesepereira.neuroteks.com/entrevista/estados_cidades.json', function (data) {

				var items = [];
				var options = '<option value="">UF</option>';	

				$.each(data, function (key, val) {
					options += '<option value="' + val.nome + '"';
					if (val.nome == '<c:out value='${client.stateClient}'/>' ) { options += " selected" };
					options += '>' + val.nome + '</option>';
				});					
				$("#state").html(options);
				$("#state").change(function () {
					var options_cidades = '';
					var str = "";					
					
					$("#state option:selected").each(function () {
						str += $(this).text();
					});
					
					$.each(data, function (key, val) {
						if(val.nome == str) {							
							$.each(val.cidades, function (key_city, val_city) {
								options_cidades += '<option value="' + val_city + '"';
								if (val_city== '<c:out value='${client.cityClient}'/>' ) { options_cidades += " selected" };
								options_cidades += '>' + val_city + '</option>';
							});							
						}
					});

					$("#city").html(options_cidades);
					
				}).change();		
			
			});
		});
	</script>		
	<script type="text/javascript">
	    $(document).ready(function () { 
	        $("#cpf").inputmask({ mask: ["999.999.999-99"], keepStatic: true });
	        $(".telephone").inputmask({ mask: ["(99) 9999-9999", "(99) 99999-9999", ], keepStatic: true });
	    });
	</script>
	<script type="text/javascript">
		var disabled = <%= request.getAttribute("disabled") %>;
		if(disabled) {
			$(":input").attr("disabled", true);
		}
	</script>
	<script type="text/javascript">
		function populateTelephonesArray() {
			telephones = "";
			$(".telephone").each( function () {
				telephones += this.value + ",";
			});
			telephones.substring(0, telephones.length - 1);
			console.log(telephones);
			$("#telephoneParameter").attr("value", telephones);
		}
	</script>
    <%@ include file="footer.jsp"%>
</html>