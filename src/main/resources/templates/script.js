document.getElementById('registerForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const responseMessage = document.getElementById('responseMessage');

    try {
        const response = await fetch('/user-api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                email: email,
                password_hash: password // 确保字段名称与后端一致
            })
        });

        let result;
        try {
            result = await response.json();
        } catch (jsonError) {
            result = {message: '服务器返回了非JSON数据。'};
        }

        if (response.status === 201) {
            responseMessage.textContent = `注册成功: 用户名 - ${result.username}, 邮箱 - ${result.email}`;
            responseMessage.style.color = 'green';
        } else if (response.status === 409) {
            responseMessage.textContent = `${result.message}: 用户名 - ${result.username}, 邮箱 - ${result.email}`;
            responseMessage.style.color = 'red';
        } else {
            responseMessage.textContent = `注册失败: 用户名 - ${result.username}, 邮箱 - ${email}`;
            responseMessage.style.color = 'red';
        }
    } catch (error) {
        responseMessage.textContent = '注册失败，请稍后再试。';
        responseMessage.style.color = 'red';
    }
});