<script>
    $(document).ready(function() {
        $("#submitButn").on("click", function() {
            var productname = $("#productname").val();
            var productcontents = $("#productcontents").val();
            var productprice = $("#productprice").val();
            var useremail = $("#useremail").val();
            var formData = new FormData();

            if(productname == "") {
                alert("상품명을 입력해주세요");
                return false;
            } else if(productprice == "") {
                alert("상품 가격을 입력해주세요");
                return false;
            } else if(isNaN(productprice)) {
                alert("숫자만 입력해주세요");
                return false;
            } else if(productcontents == "") {
                alert("상품 내용을 입력해주세요");
                return false;
            } else {
                formData.append("productname", productname);
                formData.append("productcontents", productcontents);
                formData.append("productprice", productprice);
                formData.append("useremail", useremail);
                console.log(formData);
                $.ajax({
                    url: '/product/uploadFile',
                    processData: false,
                    contentType: false,
                    data: formData,
                    datatype: 'json',
                    type: 'POST',
                    success: function(data) {
                        if(data == "f") {
                            alert("파일 업로드 실패");
                        } else {
                            $("#formsub").submit();
                        }
                    }
                });
            }
        });
    });
</script>