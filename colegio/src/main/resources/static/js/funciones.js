function eliminarCita(id) {
	swal({
		title: "Estas seguro de Eliminar la cita?",
		text: "Una vez eliminado ya no podrás encontrar la cita!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
				
				$.ajax({
					url:"/administrador/deleteCita/" + id,
					success: function(res){
						console.log(res);
					}
				});
				
				swal("Listo! La cita se elimino correctamente", {
					icon: "success",
				}).then((ok) =>{
					if(ok){						
						location.href="/administrador/listarCita";
					}
				});
			}
		});

}


function eliminarReceta(id) {
	swal({
		title: "Estas seguro de Eliminar la prescripción Médica?",
		text: "Una vez eliminado ya no podrás encontrar la prescripción!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
				
				$.ajax({
					url:"/recepcionista/deletePrescripcion/" + id,
					success: function(res){
						console.log(res);
					}
				});
				
				swal("Listo! La cita se elimino correctamente", {
					icon: "success",
				}).then((ok) =>{
					if(ok){						
						location.href="/recepcionista/listarPreMedica";
					}
				});
			} else {
				swal("Su prescripción no se eliminó!");
			}
		});

}




function eliminarPrescripcion(id) {
	swal({
		title: "Estas seguro de Eliminar la prescripción médica?",
		text: "Una vez eliminado ya no podrás encontrar la prescripción médica!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
				
				$.ajax({
					url:"/recepcionista/deletePrescripcion/" + id,
					success: function(res){
						console.log(res);
					}
				});
				
				swal("Listo! La prescipción médica se elimino correctamente", {
					icon: "success",
				}).then((ok) =>{
					if(ok){						
						location.href="/recepcionista/listarPreMedica";
					}
				});
			} else {
				swal("Your imaginary file is safe!");
			}
		});

}






function eliminarDoctor(id) {
	swal({
		title: "Estas seguro de Eliminar?",
		text: "Once deleted, you will not be able to recover this imaginary file!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
				
				$.ajax({
					url:"/administrador/deleteDoc/" + id,
					success: function(res){
						console.log(res);
					}
				});
				
				swal("Poof! Your imaginary file has been deleted!", {
					icon: "success",
				}).then((ok) =>{
					if(ok){						
						location.href="/administrador/listDoc";
					}
				});
			} else {
				swal("Your imaginary file is safe!");
			}
		});

}


