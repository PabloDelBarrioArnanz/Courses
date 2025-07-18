
// terraform init - to initialize project
resource "local_file" "products" {
  content  = "Products list updated"
  filename = "${path.module}/products.txt"
}

// terraform validate - validate the resources
// terraform plan - explanation of the changes that will be applied (executes validate before)
// terraform plan -out myPlan - export the plan to a file called myPlan
// terraform apply - to apply the changes
// terraform apply - to apply the changes
// terraform apply myPlan - apply the from the exported plan file (no plan showed neither confirmation)
// terraform show muestra el estado actual de los recursos
// terraform destroy - remove the resources created by Terraform
// terraform fmt - format the whole code
// terraform fmt myFile - format the code in myFile