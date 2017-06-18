angular.module("app").factory("notify",  NotifyService);
     
     function NotifyService() {
    	 var service = {
			 success : success,
             alert : alert,
             info : info,
             danger : danger,
             successOnSave : successOnSave,
             successOnRemove : successOnRemove
         };
         return service;
    	 
         function successOnSave() {
        	 success('Registro salvo com sucesso!');
         }
         
         function successOnRemove() {
        	 success('Registro removido com sucesso!');
         }
         
    	 function success(messageText) {
    		 PNotify.removeAll();
    		 new PNotify({
 			    title: 'Sucesso',
 			    text: messageText,
 			    type: 'success'
 			});
    	 }
    	 
    	 function alert(messageText) {
    		 PNotify.removeAll();
    		 new PNotify({
  			    title: 'Alerta',
  			    text: messageText
  			});
    	 }
    	 
    	 function info(messageText) {
    		 PNotify.removeAll();
    		 new PNotify({
  			    title: 'Informação',
  			    text: messageText,
  			    type: 'info'
  			});
    	 }
    	 
    	 function danger(messageText) {
    		 PNotify.removeAll();
    		 new PNotify({
 			    title: 'Erro',
 			    text: messageText,
 			    type: 'error'
 			});
    	 }

     }