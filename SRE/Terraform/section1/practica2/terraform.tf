resource "local_file" "products" {
  content  = "Products list"
  // Esto es una dependencia explicita, terraform aplicará primero random_string.suffix para luego poder crear este recurso
  // No importa si se declara antes o después incluso puede estar en otro fichero
  filename = "${path.module}/products-${random_string.suffix.id}.txt"
}

// random_string es un nuevo provider al hacer terraform init se instalará
resource "random_string" "suffix" {
  length  = 4
  special = false
  upper   = false
  numeric = false
}

// Solo existirá un fichero porque el sufijo random_string.suffix es el mismo para ambos recursos
resource "local_file" "other-products" {
  content  = "Other Products list"
  filename = "${path.module}/products-${random_string.suffix.id}.txt"
}

resource "random_string" "new-suffix" {
  length  = 4
  special = false
  upper   = false
  numeric = false
}

resource "local_file" "another-products" {
  content  = "Another Products list"
  filename = "${path.module}/products-${random_string.new-suffix.id}.txt"
}

// Una opción para no tener que tener un recurso diferente por cada sufijo que necesitamos es usar count
// El acceso a este recurso es como un array, por ejemplo ${random_string.multiple-suffix[0].id}
// Se puede parametrizar para por si lo necesitar usar en un bloque que tiene count también accediendo al indice ${random_string.multiple-suffix[count.index].id}
resource "random_string" "multiple-suffix" {
  length  = 4
  count = 2
  special = false
  upper   = false
  numeric = false
}

resource "local_file" "multiple-products" {
  count = 2
  content  = "Multiple Products list"
  filename = "${path.module}/multiple-products-${random_string.multiple-suffix[count.index].id}.txt"
}

// terraform show muestra el estado actual de los recursos