# Pessoa, Pessoa Física e Pessoa Jurídica

## Estratégias de solução

### Duas tabelas

1. Criar uma sequence (sq_pessoa_id) para que tanto pessoa_fisica quanto
pessoa_juridica possam compartilhar o mesmo source id

2. Criar duas tabelas: pessoa_fisica e pessoa_juridica. Em cada uma, salvar os
dados relavantes de cada objeto

3. 