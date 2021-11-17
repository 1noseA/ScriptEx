<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Grape Land</title>
<link rel="stylesheet" href="css/comment.css" type="text/css" />
</head>
<body>
	<header>
		<h1>一瀬ぶどう園</h1>
	</header>

	<div class="show">
		<img src="./images/budou.jpg" alt="ぶどう園" title="ぶどう園">
	</div>

	<h2>● ぶどうの品種 ●</h2>

	<div class="kind">
		<div class="wrap">
			<img src="./images/kyohou.jpg" alt="巨峰" title="巨峰">
			<div class="box">
				<h3>巨峰</h3>
				<p>果肉はしまっていて多汁。<br>
				香りがよく、甘みが強くてやさしい酸味。<br>
				「巨峰」は商標名（商品名）で、品種名は「石原センテニアル」。</p>
			</div>
		</div>

		<div class="wrap">
			<img src="./images/shain.jpg" alt="シャイン" title="シャイン">
			<div class="box">
				<h3>シャインマスカット</h3>
				<p>皮ごと食べられるのが特徴。<br>
				パリッとした歯ごたえで、ジューシーで上品な甘みと高貴な香り。</p>
			</div>
		</div>

		<div class="wrap">
			<img src="./images/kaiji.jpg" alt="甲斐路" title="甲斐路">
			<div class="box">
				<h3>甲斐路</h3>
				<p>「紅いマスカット」ともいわれる。<br>
				ほのかにマスカットのような香りがあり、深みのある甘さとみずみずしさが特徴。</p>
			</div>
		</div>
	</div>

	<h2>● お客さまからのコメント ●</h2>
	<!-- コメント表示 -->
	<div class="comment-lists">
		<c:forEach items="${ list }" var="com">
			<div class="com-list">
				<span>コメント<c:out value="${ com.id }" />：</span>
				<fmt:formatDate value="${ com.date }" />
				<span>★<c:out value="${ com.name }" />さん★</span>
				<br>
				<span><c:out value="${ com.content }" /></span>
				<br>
				<c:if test="${ reply != null }">
					<!-- 返信表示 -->
					<div class="reply-lists">
						<c:forEach items="${ reply }" var="rep">
							<c:if test="${ com.id == rep.comId }">
								<hr width="100%">
								<div class="rep-list">
									<span>返信<c:out value="${ rep.repId }" />：</span>
									<fmt:formatDate value="${ rep.repDate }" />
									<span><c:out value="${ rep.repName }" />より</span>
									<br>
									<span><c:out value="${ rep.repContent }" /></span>
									<br>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:if>

				<h4>返信する</h4>
				<!-- 返信入力フォーム -->
				<div class="reply-form">
					<form action="reply" method="post">
						<p>お名前<br><input type="text" name="repName" class="name" required /></p>
						<p>コメント<br><textarea name="repContent" class="content" required></textarea></p>
						<input type="hidden" name="comId" value="${ com.id }" />
						<input type="submit" value="送信" class="submit-button" />
					</form>
				</div>
			</div>
		</c:forEach>
	</div>

	<h5>＊＊コメントお待ちしております＊＊</h5>
	<!-- コメント入力フォーム -->
	<div class="comment-form">
		<form action="comment" method="post">
			<p>お名前<br><input type="text" name="name" class="name" required /></p>
			<p>コメント<br><textarea name="content" class="content" required></textarea></p>
			<input type="submit" value="送信" class="submit-button" />
		</form>
	</div>

	<footer>
		<h3>ぶどう狩りしようぜ！！</h3>
	</footer>
</body>
</html>