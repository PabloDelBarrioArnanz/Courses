
// terraform init - to initialize project
resource "local_file" "products" {
  content  = "Products list updated"
  filename = "${path.module}/products.txt"
}

// terraform plan - explanation of the changes that will be applied
// terraform plan -out myPlan - export the plan to a file called myPlan
// terraform apply - to apply the changes
// terraform apply - to apply the changes
// terraform apply myPlan - apply the from the exported plan file (no plan showed neither confirmation)
// terraform destroy - remove the resources created by Terraform