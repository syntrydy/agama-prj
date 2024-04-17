[#ftl output_format="HTML"]
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>SAML_IDP_Discovery_Form
	</title><link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2/dist/tailwind.min.css" type="text/css"></head>
<body>

  
	
	
	
	
	
  
  
	<div class="md:h-screen bg-white relative flex flex-col justify-center items-center">
	  <div class="md:border md:border-gray-300 bg-white md:shadow-lg shadow-none rounded p-10 align-middle">
		<div class="flex flex-col items-center space-y-3">
		  <div class="flex">
			<img src="https://gluu.org/wp-content/uploads/2020/12/logo.png">
		  </div>
		  <span class="text-2xl font-semi-bold leading-normal">Form</span>
		  <p class="leading-normal">Sign in with
		  </p>
		</div>
		<form method="post" class="my-8">
		  <input type="hidden" id="extIdpRedirectUrl" name="extIdpRedirectUrl">
		  <div class="relative mb-2">
			[#if idps?has_content]
			[#list idps as idp]
			<b>${idp.alias}
			</b>
			${idp.displayName}
			<br>
			${idp.singleSignOnServiceUrl}
			<br>
			&lt;#assign internalId&gt;${idp.internalId}
			<!--#assign-->
			<a href="${internalId}" rel="link">${idp.alias}</a>
			[/#if]
			[/#list]
		  </div>
		  <div class="space-y-9">
			<div class="text-sm flex justify-center items-center">
			  <button type="submit" class="py-2 px-6 rounded text-white btn bg-green-500 hover:bg-green-600">
				Submit
			  </button>
			</div>
		  </div>
		</form>
	  </div>
	</div>
  
  
</body>

<script>
	window.onload = function () {
	  var form = document.querySelector("form");
	  form.onsubmit = handleFormValidation;
	};
	function handleFormValidation() {
	  return true
	}
	function showLink(thisLink){
	  alert(thisLink.innerHTML);
	  alert(thisLink.value);
	}
  </script>
</html>