
// terraform init - to initialize project
resource "local_file" "products" {
  content  = "Products list updated"
  filename = "${path.module}/products.txt"
}

// terraform plan - explanation of the changes that will be applied
// terraform apply - to apply the changes
// terraform destroy - remove the resources created by Terraform